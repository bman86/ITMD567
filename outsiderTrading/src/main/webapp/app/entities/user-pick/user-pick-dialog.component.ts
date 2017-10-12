import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { UserPick } from './user-pick.model';
import { UserPickPopupService } from './user-pick-popup.service';
import { UserPickService } from './user-pick.service';
import { User, UserService } from '../../shared';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-user-pick-dialog',
    templateUrl: './user-pick-dialog.component.html'
})
export class UserPickDialogComponent implements OnInit {

    userPick: UserPick;
    isSaving: boolean;

    users: User[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private userPickService: UserPickService,
        private userService: UserService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.userService.query()
            .subscribe((res: ResponseWrapper) => { this.users = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.userPick.id !== undefined) {
            this.subscribeToSaveResponse(
                this.userPickService.update(this.userPick));
        } else {
            this.subscribeToSaveResponse(
                this.userPickService.create(this.userPick));
        }
    }

    private subscribeToSaveResponse(result: Observable<UserPick>) {
        result.subscribe((res: UserPick) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: UserPick) {
        this.eventManager.broadcast({ name: 'userPickListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackUserById(index: number, item: User) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-user-pick-popup',
    template: ''
})
export class UserPickPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userPickPopupService: UserPickPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.userPickPopupService
                    .open(UserPickDialogComponent as Component, params['id']);
            } else {
                this.userPickPopupService
                    .open(UserPickDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
